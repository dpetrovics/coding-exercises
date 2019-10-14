(ns coding-exercises.test.core
  (:require [clojure.test :refer [is testing] :as t])
  (:use [coding-exercises.core]))

(defn test-func
  "Creates a function that validate result against the result of applying func with input"
  ([func pass?]
   (fn [input result]
     (let [actual (if (vector? input) (apply func input) (func input))]
       (is (pass? actual result) 
           (format "(func %s) expected to result %s but found %s" input result actual)))))
  ([func]
   (test-func func =)))

(defn run-tests!
  "Funs all the test test cases by applying each element in tests"
  ([tests test-func]
   (run! #(apply test-func %1) tests))
  ([tests test-func desc]
   (testing desc
     (run-tests! tests test-func))))

(comment defmacro deftest 
  [desc tests func pass?] 
  (list macroexpand 
        '(t/deftest testing-something
           (testing 'desc (run-tests! 'tests (test-func 'func 'pass?))))))

(t/deftest test-finonacii
  ; nth fibonacii
  (let [tests [[1 1] [2 1] [3 2] [4 3] [5 5] [14 377]]
        tests-base-case [[0 0] [1 1] [-1 0] [-19900090090 0]]
        func (test-func basic-fib)]
    (run-tests! tests func  "if the correct nth fibonacii is returned")
    (run-tests! tests-base-case func "Test if the base case is returned for invalid input")))

(t/deftest test-user-followers
  (let [tests [
               [(list '("user" "follower1") '("user" "follower2") '(:user-2 "follower1"))
                {"user" #{"follower1", "follower2"} :user-2 #{"follower1"}}]
               [(list [:user :follower] [:user :follower-2])
                {:user #{:follower :follower-2}}]]
        func (test-func user-followers)]
    (run-tests! tests func "Test tupples are mapped correctly")))

(t/deftest test-lazy-finonacii
  (let [tests [[1 1] [2 1] [3 2] [4 3] [5 5] [14 377]]
        lazy-fib-list (lazy-fib)
        func (test-func #(nth lazy-fib-list %1))]
    (run-tests! tests func  "if the correct nth fibonacii is returned")))

(t/deftest test-test-word-frequencies
  (let [tests [["this is a hello world example that is new."
                {"this" 1 "that" 1 "a" 1 "hello" 1 "world" 1 "is" 2 "example" 1 "new" 1}]
               ["This Is a Hello world example that is new."
                {"this" 1 "that" 1 "a" 1 "hello" 1 "world" 1 "is" 2 "example" 1 "new" 1}]]
        func (test-func word-frequencies)]
    (run-tests! tests func "Testing if word frequencies")))

(t/deftest test-power
  (let [tests [[[2 2] 4] [[3 2] 9] [[4 4] 256] [[100 3] 1000000] [[7 3] 343]]
        func (test-func power)]
    (run-tests! tests func "Testing if power is calculated correctly")))

(t/deftest test-clock-angle
  (let [tests [[[0 0] 0.0] [[3 0] 90.0] [[6 0] 180.0]]
        func (test-func clock-angle)]
    (run-tests! tests func "Testing if clock angle is calculted correctly")))

(t/deftest test-fizz-buzz 
  (let [tests [[2 "2"] [3 "Fizz"] [4 "4"] [5 "Buzz"] [6 "Fizz"] [7 "7"]
               [8 "8"] [9 "Fizz"] [10 "Buzz"] [11 "11"] [12 "Fizz"] [13 "13"]
               [14 "14"] [15 "FizzBuzz"] [16 "16"] [17 "17"] [18 "Fizz"] [19 "19"]
               [20 "Buzz"] [21 "Fizz"] [22 "22"] [23 "23"]]
        func (test-func fizz-buzz)]
    (run-tests! tests func  "Fizz Buzz is returned correctly")))

(t/deftest test-insert-letter
  (let [tests [[["hello" "i"] "hieililioi"] [["you" "j"] "yjojuj"]]
        f (test-func insert-letter)]
    (run-tests! tests f "testing letter insrtion")))

(t/deftest test-word-permutations
  (let [tests [["hel" (list "hel" "hle" "elh" "ehl" "lhe" "leh" )]]
        f (test-func word-permutations)]
    (run-tests! tests f "testing letter insrtion")))
