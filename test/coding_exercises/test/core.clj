(ns coding-exercises.test.core
  (:use [coding-exercises.core]
        [clojure.test]))


(deftest test-return-number 
  (testing "Fizz Buzz is returned correctly"
    ;     (is (= (fizz-buzz 3) "Fizz"))
    (let [tests [[2 "2"] [3 "Fizz"] [4 "4"] [5 "Buzz"] [6 "Fizz"] [7 "7"]
                 [8 "8"] [9 "Fizz"] [10 "Buzz"] [11 "11"] [12 "Fizz"] [13 "13"]
                 [14 "14"] [15 "FizzBuzz"] [16 "16"] [17 "17"] [18 "Fizz"] [19 "19"]
                 [20 "Buzz"] [21 "Fizz"] [22 "22"] [23 "23"]]
          fun (fn [input result] (is (= (fizz-buzz input) result)))]
      (run! #(apply fun %1) tests))))

