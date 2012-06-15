# multifn-perf

A one-off throwaway project to test a patch for Clojure issue 988, concerning multimethod locking.

## Usage

Just run 

```
lein2 perforate
```

## Sample Output

```
Benchmarking profiles:  [:clj1.5]
======================
Goal:  A multi-threaded high-contention multifn.
-----
Case:  :two-threads
Evaluation count             : 120
             Execution time mean : 606.506892 ms  95.0% CI: (606.456592 ms, 606.548225 ms)
    Execution time std-deviation : 7.994298 ms  95.0% CI: (7.946739 ms, 8.031992 ms)
         Execution time lower ci : 598.162500 ms  95.0% CI: (598.162500 ms, 598.162500 ms)
         Execution time upper ci : 615.675725 ms  95.0% CI: (615.603000 ms, 615.675725 ms)

Case:  :four-threads
Evaluation count             : 60
             Execution time mean : 1.155125 sec  95.0% CI: (1.155004 sec, 1.155207 sec)
    Execution time std-deviation : 13.424155 ms  95.0% CI: (13.267639 ms, 13.590792 ms)
         Execution time lower ci : 1.138701 sec  95.0% CI: (1.138452 sec, 1.138701 sec)
         Execution time upper ci : 1.172263 sec  95.0% CI: (1.172263 sec, 1.172287 sec)

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 1 (1.6667 %)
	low-mild	 1 (1.6667 %)
	high-mild	 1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Benchmarking profiles:  [:clj1.5-lockless]
======================
Goal:  A multi-threaded high-contention multifn.
-----
Case:  :two-threads
Evaluation count             : 420
             Execution time mean : 159.548724 ms  95.0% CI: (159.519490 ms, 159.582412 ms)
    Execution time std-deviation : 8.573307 ms  95.0% CI: (8.490968 ms, 8.635948 ms)
         Execution time lower ci : 155.892714 ms  95.0% CI: (155.892714 ms, 155.892714 ms)
         Execution time upper ci : 165.053307 ms  95.0% CI: (165.013857 ms, 165.053307 ms)

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 9.3687 % Variance is slightly inflated by outliers

Case:  :four-threads
Evaluation count             : 300
             Execution time mean : 218.631273 ms  95.0% CI: (218.559650 ms, 218.709873 ms)
    Execution time std-deviation : 19.643749 ms  95.0% CI: (19.502026 ms, 19.748559 ms)
         Execution time lower ci : 208.244600 ms  95.0% CI: (208.244600 ms, 208.244600 ms)
         Execution time upper ci : 237.158360 ms  95.0% CI: (237.080200 ms, 237.158360 ms)

Found 10 outliers in 60 samples (16.6667 %)
	low-severe	 9 (15.0000 %)
	low-mild	 1 (1.6667 %)
 Variance from outliers : 27.0385 % Variance is moderately inflated by outliers

```

## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
