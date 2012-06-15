(defproject multifn-perf "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [perforate "0.2.3"]]

  :plugins [[perforate "0.2.3"]]

  :repositories {"sonatype" {:url "http://oss.sonatype.org/content/repositories/releases"
                             :snapshots false
                             :releases {:checksum :fail :update :always}}
                 "sonatype-snapshots" {:url "http://oss.sonatype.org/content/repositories/snapshots"
                                       :snapshots true
                                       :releases {:checksum :fail :update :always}}}

  :profiles {:clj1.5 {:dependencies [[org.clojure/clojure
                                      "1.5.0-master-SNAPSHOT"]]}
             :clj1.5-lockless {:dependencies [[org.clojure/clojure
                                               "1.5.0-master-SNAPSHOT-lockless-multifn"]]}}

  :perforate {:environments [{:name :locking-multifn
                              :profiles [:clj1.5]
                              :namespaces [multifn-perf.bench]}
                             {:name lockless-multifn
                              :profiles [:clj1.5-lockless]
                              :namespaces [multifn-perf.bench]}]})

