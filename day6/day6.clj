(def input [5 1 10 0 1 7 13 14 3 12 8 10 7 12 0 6])

(defn redistribute [banks]
  (let [max-val (apply max banks)
        max-idx (->> banks
                     (map-indexed vector)
                     (filter (comp #{max-val} second))
                     ffirst)
        next-idx (fn [v i] (mod (inc i) (count v)))]
    (->> [(assoc banks max-idx 0) (next-idx banks max-idx)]
         (iterate (fn [[coll i]]
                    [(update coll i inc) (next-idx coll i)]))
         next
         (take max-val)
         last
         first)))

(defn solve [input]
  (reduce (fn [[seen banks] steps]
            (if (seen banks)
              (reduced [steps (seen banks)])
              [(assoc seen banks steps) (redistribute banks)]))
          [{} input]
          (range)))

(defn day6 []
  (println "part1 : " (first (solve input)))
  (println "part2 : " (apply - (solve input))))

