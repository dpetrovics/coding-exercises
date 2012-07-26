(ns coding-exercises.core
  (:require [clojure.string :as str]))

(defn basic-fib
  ^{:doc "Returns the n-th number in the fibonacci sequence."}
  [num]
  (if (= num 0)
    0
    (if (= num 1)
      1
      (+ (basic-fib (- num 2))
         (basic-fib (- num 1))))))

(defn lazy-fib
  ^{:doc "Returns a lazy sequnence of the fibonacci sequence. Use with 'take' or 'nth'"}
  []
  (map first
       (iterate (fn [[a b]] [b (+ a b)]) [0 1])))


