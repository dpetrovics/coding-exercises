(ns chapter1
  [:use coding-exercises.core]
  [:require [clojure.string :as s]])

(def problem1
  "Implement an algorithm to determine if a string has all unique
  characters What if you can not use additional data structures?")

(comment
  "SEE JAVA file for solution. There are 256 ASCII
  characters (character encoding scheme). You could create an array
  with 256 slots, and then make array[current-char] = true as you
  iterate through the chars in the word. As you iterate, check if
  array[current-char] already = true, in which case there is a repeat
  char (ie not unique). There are 256 ASCII chars because each ASCII
  char gets one byte (8bits, ie 2^8 combos). Unicode gets more bytes
  so it can support more chars.")

(defn unique-chars?
  ^{:doc "Problem with this solution is that it
  goes all the way through the string, even if a character has already
  been repeated. (It builds the whole frequency map before it checks
  the count)"}
  [s]
  (every? #(= 1 %1)
          (vals
           (frequencies s))))

(defn unique-chars-bit
  "Uses one long to store a bit vector. This means we have 8 bytes (64 bits) available for storage. Only A-Z, a-z are allowed in the input string, no numbers or special chars."
  [s]
  
  )

(def problem2
  "Write code to reverse a string.")

(defn my-reverse
  [word]
  (apply str
         (for [i (range 0 (count word))]
           (.charAt word 
                    (- (dec (count word)) 
                       i)))))

(defn another-reverse
  [word]
  (apply str
         (reduce conj () word)))

(comment
  "This would be easiest to do with (reverse word).")

(def problem3
  "Design an algorithm and write code to remove the duplicate
  characters in a string without using any additional buffer. Assume
  this means keep the first time a char appears, but remove any
  subsequent appearances. SEE SOLUTION IN JAVA FILE")


(def problem4
  "Write a method to decide if two strings are anagrams or not.")

;;clojure is awesome for this problem
(defn anagrams?  ^{:doc "Takes in two strings and returns true if they
  are anagrams, false otherwise."}
  [s1 s2]
  (= (frequencies s1)
     (frequencies s2)))

(defn anagrams2?
  ^{:doc "Checks to see if two strings are anagrams, ignores whitespace and capitalization." }
  [s1 s2]
  (= (frequencies (s/replace (s/lower-case s1) " " ""))
     (frequencies (s/replace (s/lower-case s2) " " ""))))
