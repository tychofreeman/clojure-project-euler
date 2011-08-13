(ns howtotest.problem-61
	(:require clojure.contrib.math)
)

(defn triangle [n]
  (/ (* n (+ n 1)) 2 )
)

(defn get-set-of [f n]
	(set (take-while #(<= % n)
		(map f (range 1 (+ 1 n)))))
)

(defn is-triangle [n]
	(contains? (get-set-of triangle n) n))

(defn square [n]
  (* n n)
)

(defn is-square [n]
	(contains? (get-set-of square n) n))

(defn pentagonal [n]
  (/ (* n (- (* n 3) 1)) 2)
)

(defn is-pentagonal [n]
	(contains? (get-set-of pentagonal n) n))

(defn hexagonal [n]
  (* n (- (* n 2) 1))
)

(defn is-hexagonal [n]
	(contains? (get-set-of hexagonal n) n))

(defn heptagonal [n]
  (/ (* n (- (* 5 n) 3)) 2)
)

(defn is-heptagonal [n]
	(contains? (get-set-of heptagonal n) n))

(defn octagonal [n]
  (* n (- (* n 3) 2))
)

(defn is-octagonal [n]
	(contains? (get-set-of octagonal n) n))


(def triangles   (set (filter #(< 999 %) (get-set-of triangle   10000))))
(def squares     (set (filter #(< 999 %) (get-set-of square     10000))))
(def pentagonals (set (filter #(< 999 %) (get-set-of pentagonal 10000))))
(def hexagonals  (set (filter #(< 999 %) (get-set-of hexagonal  10000))))
(def heptagonals (set (filter #(< 999 %) (get-set-of heptagonal 10000))))
(def octagonals  (set (filter #(< 999 %) (get-set-of octagonal  10000))))


; Markov

(defn markov-match [x y]
	(let [last-x (rem x 100)
		first-y (int (/ y 100))]

		(== last-x first-y)
	)
)

(defn is-markov-match? [ns candidate]
	(if (empty? ns)
		true
		(markov-match (last ns) candidate))
)

; Solve the problem ;

; forward decl
(defn solve-for-set)

(defn solve-for [ns sets]
	(if (empty? sets)
		(if (markov-match (last ns) (first ns)) ns [])
		(map #(solve-for-set ns % (disj (set sets) %)) sets)
	)
)

(defn solve-for-set [ns first-set rest-sets]
	(flatten
		(map
			(fn [i] (solve-for (concat ns [i]) rest-sets))
			(filter #(is-markov-match? ns %) first-set)
		)
	)
)

;(defn solve [sets]
;	(map #(apply solve-for %)
;		([[1000] sets])
;	)
;)
