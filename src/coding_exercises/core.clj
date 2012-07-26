(ns coding-exercises.core
  (:require [clojure.string :as str]
            [clojure.math.numeric-tower :as math]))

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

(defn clock-angle
  ^{:doc "Finds the angle in degrees between the hour hand and minute hand."}
  [hr min]
  (assert (and (< min 60) (>= min 0)) "invalid minute")
  (let [min-angle (* min
                     (/ 360 60))
        hr-angle (* (+ (mod hr 12) (/ min 60))
                    (/ 360 12))]
    (float
     (math/abs (- hr-angle min-angle)))))


