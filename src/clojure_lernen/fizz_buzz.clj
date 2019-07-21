(ns clojure-lernen.fizz-buzz
  (:gen-class))

(defn multiple?
  [n number]
  (zero? (mod n number)))

(defn multiple-of-3?
  [n]
  (multiple? n 3)) 

(defn multiple-of-5?
  [n]
  (multiple? n 5)) 

(defn fizz-buzz 
  [number]
  (let [res (str 
              (if (multiple-of-3? number)
                "Fizz")
              (if (multiple-of-5? number)
                "Buzz"))]
    (if (clojure.string/blank? res)
      (str number)
      res)))

