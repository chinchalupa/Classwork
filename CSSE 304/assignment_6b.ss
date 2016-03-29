(define filter-in
	(lambda (pred ls)
		(if (null? ls)
			'()
			(if (pred (car ls))
				(cons (car ls) (filter-in pred (cdr ls)))
				(filter-in pred (cdr ls))
			)
		)
	)
)

(define filter-out
	(lambda (pred ls)
		(if (null? ls)
			'()
			(if (pred (car ls))
				(filter-out pred (cdr ls))
				(cons (car ls) (filter-out pred (cdr ls)))

			)
		)
	)
)

(define sort-list-of-symbols
	(lambda (ls)
		(define stringed (map symbol->string ls))
		(sort string<? stringed)
	)
)

(define invert
	(lambda (ls)
		(map reverse ls)
	)
)

(define vector-index
	(lambda (pred ls)
		(list-indexer pred (vector->list ls))
	)
)

(define list-indexer
	(lambda (pred ls)
		(if (ormap pred ls)
			(if (pred (car ls))
				0
				(+ 1 (list-indexer pred (cdr ls)))
			)
			#f
		)
	)
)
