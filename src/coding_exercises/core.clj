(ns coding-exercises.core
  (:require [clojure.set :as set :only [union]]
            [clojure.math.numeric-tower :as math]))

(defn basic-fib
  ^{:doc "Returns the n-th number in the fibonacci sequence."}
  [num]
  (if (<= num 0)
    0
    (if (= num 1)
      1
      (+ (basic-fib (- num 2))
         (basic-fib (- num 1))))))

;(defn basic-fib
;  ^{:doc "Returns the n-th number in the fibonacci sequence."}
;  [num])

(defn user-followers
  "You have a collection of 2-tuples (user, follower). You want to get out (user, [followers]). Solution: put them all into a map, and use merge-with."
  [input]
  (reduce (fn [r n]
            (assoc r
                   (first n)
                   (set (conj (r (first n)) (last n)))))
          {} input))

(defn user-followers
  [uf-list]
  (let [usr-map (map #(hash-map (first %1) #{(last %1)}) uf-list)]
    (apply merge-with
           #(set/union %1 %2)
           usr-map)))

;(defn user-followers
;  "You have a collection of 2-tuples (user, follower). You want to get out (user, [followers]). Solution: put them all into a map, and use merge-with."
;  [input])

(defn lazy-fib
  ^{:doc "Returns a lazy sequnence of the fibonacci sequence. Use with 'take' or 'nth'"}
  []
  (map first
       (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

;(defn lazy-fib
;  ^{:doc "Returns a lazy sequnence of the fibonacci sequence. Use with 'take' or 'nth'"}
;  [])

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

;(defn clock-angle
;  ^{:doc "Finds the angle in degrees between the hour hand and minute hand."}
;  [hr min])

(defn word-frequencies
  ^{:doc "Create a function to accept a string and return a map of word (case insensitive) and its frequency"}
  [sentence]
  (reduce
    (fn [res w] 
      (let [wrd (clojure.string/lower-case w)]
        (assoc res (clojure.string/lower-case wrd)
               (inc (get res wrd 0)))))
    {}
    (re-seq #"\w+" sentence)))

; (defn word-frequencies;
;   ^{:doc "Create a fun;ction to accept a string and return a map of word and its frequency"}
;   [sentence])

(defn power [base exp]
  (nth
    (iterate #(* %1 base) 1) exp))

;(defn power
;  "Write a function to calculate the power of a number using iterate"
;  [base exp])

(defn fizz-buzz 
  [number]
  (let [multiple? (fn  [n number]
                   (zero? (mod n number)))
        res (str 
              (if (multiple? number 3) "Fizz")
              (if (multiple? number 5)
                "Buzz"))]
    (if (clojure.string/blank? res)
      (str number)
      res)))

;(defn fizz-buzz [number]
;  "Write a program that prints the numbers from 1 to 100.  But for
;   multiples of three print \"Fizz\" instead of the number and for the
;   multiples of five print \"Buzz\". For numbers which are multiples of
;   both three and five print \"FizzBuzz\""
;   ())

(defn insert-letter
  ^{:doc "Returns a collection of each new word produced by inserting the letter in all possible spots in the word."}
  [word letter]
  (let [length (count word)]
    (for [num (range 0 (inc length))]
      (str (subs word 0 num)
           letter
           (subs word num length)))))

(defn word-permutations
  ^{:doc "Returns a collection of all permutations of the given word."}
  [s]
  (if (= (count s) 1)
    (list s)
    (let [subword (subs s 0 (dec (count s)))  ;;ex: 'bar', subword = 'ba'
          letter (str (last (seq s)))]  ;;the last letter, ie 'r' in 'bar'
      (apply concat
             (for [word (word-permutations subword)]
               (insert-letter word letter))))))
