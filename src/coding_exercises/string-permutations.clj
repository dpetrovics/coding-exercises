(ns string-permutations
  (:require [clojure.string :as str]))

(def problem-statement
  "Example: Design an algorithm to print all permutations of a string.")

(comment
  "The algorithm checks to see if the word is of length one, if so,
  then just return a list with that word. If the word is longer, chop
  off the last letter, and insert it in every spot in all permutations
  of the word minus the last letter. It is a recursive solution.")

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

(defn factorial
  ^{:doc "Returns the factorial of a number."}
  [num]
  {:pre [(>= num 1)]}
  (if (= num 1)
    1
    (* num (factorial (dec num)))))

(defn num-permutations
  ^{:doc "Displays the number of permutations for a given word"}
  [word]
  (factorial
   (count word)))
