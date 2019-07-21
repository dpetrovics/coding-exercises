(ns coding-exercises.core)

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

(def map-example
  "You have a collection of 2-tuples (user, follower). You want to get out (user, [followers]). Solution: put them all into a map, and use merge-with.")

(def inputs
  '(("Angie" "Dave") ("Angie" "Max") ("Bill" "Joe") ("Joe" "Moe") ("Bill" "Dave") ("Mike" "Dave") ("Angie" "Bill")))

(defn user-followers
  [uf-list]
  (let [usr-map (map #(apply hash-map %1) uf-list)]
    (apply merge-with
           #(if (seq? %1)
              (conj %1 %2)
              (list %1 %2))
           usr-map)))

;(defn clock-angle
;  ^{:doc "Finds the angle in degrees between the hour hand and minute hand."}
;  [hr min]
;  (assert (and (< min 60) (>= min 0)) "invalid minute")
;  (let [min-angle (* min
;                     (/ 360 60))
;        hr-angle (* (+ (mod hr 12) (/ min 60))
;                    (/ 360 12))]
;    (float
;     (math/abs (- hr-angle min-angle)))))

(defn word-frequencies
  ^{:doc "My implementation of the Clojure 'frequency' function. Takes in a sentence (string) and outputs a map with words as keys and their frequency as values."}
  [sentence]
  (reduce
   (fn [res wrd] (assoc res wrd
                       (inc (get res wrd 0))))
   {}
   (re-seq #"\w+" sentence)))

(defn power [base exp]
  (nth
   (iterate #(* %1 base) 1) exp))

(defn multiple?
  [n number]
  (zero? (mod n number)))

;(defn fizz-buzz [number]
;  "Write a program that prints the numbers from 1 to 100.  But for
;   multiples of three print \"Fizz\" instead of the number and for the
;   multiples of five print \"Buzz\". For numbers which are multiples of
;   both three and five print \"FizzBuzz\""
;   ())

(defn fizz-buzz 
  [number]
  (let [res (str 
              (if (multiple? number 3) "Fizz")
              (if (multiple? number 5)
                "Buzz"))]
    (if (clojure.string/blank? res)
      (str number)
      res)))

