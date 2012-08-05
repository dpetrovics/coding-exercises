(ns chapter5
  [:use coding-exercises.core])

;;BITWISE OPERATIONS

(defn my-encode
  ^{:doc "Takes in an age (0-127), a gender (0 or 1), and a height (0-127), and encodes them in a 2byte (16bit) short."}
  [age gender height]
  (short
   (bit-or 
    (bit-shift-left 
     (bit-or 
      (bit-shift-left age 1)
      gender) 
     7)
    height)))

(defn my-decode
  ^{:doc "Takes in a short encoded with (my-encode) and spits out a str with the decoded age. height, and gender."}
  [num]
  (str "age: "
       (bit-shift-right num 8)
       ", gender: "
       (bit-and
        (bit-shift-right num 7) 1)
       ", height: "
       (bit-and num 127)))

(defn binStr
  ^{:doc "Outputs the binary string representation of the number."}
  [num]
  (. Long toBinaryString num))
