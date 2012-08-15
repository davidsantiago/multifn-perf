(ns multifn-perf.bench
  (:use perforate.core))

(defgoal contended-mfn "A multi-threaded high-contention multifn.")

;; Multimethod version
(defmulti add-something even?)
(defmethod add-something true [x] (+ x 0))
(defmethod add-something false [x] (+ x 1))

;; Regular function version
(defn add-something-fn [x]
  (if (even? x)
    (+ x 0)
    (+ x 1)))

;; Protocol versions
(defprotocol SomethingAddable
  (add-something-prot [this x]))
(defrecord InlineRec []
  SomethingAddable
  (add-something-prot [r x] (if (even? x) (+ x 0) (+ x 1))))
(defrecord ExtendedRec [])
(extend-type ExtendedRec
  SomethingAddable
  (add-something-prot [r x] (if (even? x) (+ x 0) (+ x 1))))

(defn work []
  (loop [acc 0
         cntr 2000000]
    (if (= 0 cntr)
      acc
      (recur (+ acc (add-something cntr))
             (dec cntr)))))

(defn work-prot [rec]
  (loop [acc 0
         cntr 2000000]
    (if (= 0 cntr)
      acc
      (recur (+ acc (add-something-prot rec cntr))
             (dec cntr)))))

(defcase contended-mfn :regular-fn
  []
  (loop [acc 0 cntr 2000000]
    (if (= 0 cntr)
      acc
      (recur (+ acc (add-something-fn cntr))
             (dec cntr)))))

(defcase contended-mfn :one-thread
  []
  (work))

(defcase contended-mfn :two-threads
  []
  (let [f1 (future (work))
        f2 (future (work))]
    (+ @f1 @f2)))

(defcase contended-mfn :four-threads
  []
  (let [f1 (future (work))
        f2 (future (work))
        f3 (future (work))
        f4 (future (work))]
    (+ @f1 @f2 @f3 @f4)))

(defcase contended-mfn :inline-protocol
  []
  (let [rec (->InlineRec)]
    (work-prot rec)))

(defcase contended-mfn :extended-protocol
  []
  (let [rec (->ExtendedRec)]
    (work-prot rec)))