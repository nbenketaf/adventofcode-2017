(defn solve [coll f]
  (loop [coll coll
         index 0
         res 0]
        (if-let [offset (get coll index)]
          (recur (update coll index (f offset)) (+ index offset) (inc res))
          res)))

(defn parse [filename]
  (->> filename
       slurp
       clojure.string/split-lines
       (mapv read-string)))

(defn day5 [filename]
  (let [input (parse filename)]
    (println "part1 = " (solve input (constantly inc)))
    (println "part2 = " (solve input #(if (> % 2) dec inc)))))
