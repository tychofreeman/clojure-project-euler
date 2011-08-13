(ns howtotest.test.problem-61-test
    (:use clojure.test)
    (:use howtotest.problem-61)
    (:use midje.sweet)
)


(fact (get-set-of triangle 15) => #{1 3 6 10 15})
(fact (get-set-of triangle 15) => #{1 3 6 10 15})
(fact (get-set-of square 25) => #{1 4 9 16 25})
(fact (get-set-of pentagonal 35) => #{1 5 12 22 35})
(fact (get-set-of hexagonal 45) => #{1 6 15 28 45})
(fact (get-set-of heptagonal 55) => #{1 7 18 34 55})
(fact (get-set-of octagonal 65) => #{1 8 21 40 65})

(fact (triangle 127) => 8128)
(fact (square 91) => 8281)
(fact (pentagonal 44) => 2882)

(fact (is-triangle 1) => true)
(fact (is-triangle 8128) => true)
(fact (is-triangle 15) => true)
(fact (is-triangle 16) => false)

(fact (is-square 8281) => true)
(fact (is-square 64) => true)
(fact (is-square 63) => false)

(fact (is-pentagonal 2882) => true)
(fact (is-pentagonal 2881) => false)

;we're removing all pentagonals below 1000
(fact (contains? pentagonals 1) => false)
(fact (contains? pentagonals 2882) => true)
(fact (some #{2882} pentagonals) => 2882)

;(fact (solve [triangles squares pentagonals]) => #{8128 2882 8281})

(fact (solve-for [8128 2882] [squares]) => [8128 2882 8281])
(fact (solve-for [8128] [pentagonals squares]) => [8128 2882 8281])
(fact (solve-for [] [triangles pentagonals squares]) => [8128 2882 8281])
(fact (solve-for [] [pentagonals triangles squares]) => [8128 2882 8281])

(fact (solve-for [] [triangles squares pentagonals hexagonals heptagonals octagonals]) => [])


(fact (markov-match 8128 2882) => true)
(fact (markov-match 8128 2182) => false)
