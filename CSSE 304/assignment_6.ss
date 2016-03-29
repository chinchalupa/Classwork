(define curry2
	(lambda (op)
		(lambda (arg)
			(lambda (arg2)
				(op arg arg2)
			)
		)
	)
)

(define curried-compose
	(lambda (op)
		(lambda (op2)
			(lambda (ls)
				(op (op2 ls))
			)
		)
	)
)

(define compose
	(lambda ops
		(lambda (ls)
			(if (eq? (length ops) 2)
				((car ops) ((cadr ops) ls))
				((car ops) ((cadr ops) ((caddr ops) ls)))
			)
		)
	)
)


(define make-list-c
	(lambda (times)
		(lambda (num)
			(make-list times num)
		)
	)
)

(define let->application
	(lambda (ls)
		(if (eq? (car ls) (quote let))
			(append (list (list 'lambda (map car (cadr ls)) (caddr ls))) (map cadr (cadr ls)))
		)
	)
)

(define let*->let
	(lambda (ls)
		(if (eq? (car ls) 'let*)
			(make-let-list (cadr ls) (caddr ls))
		)
	)
)

(define make-let-list
	(lambda (ls fin)
		(if (null? ls)
			fin
			(list 'let (list (car ls)) (make-let-list (cdr ls) fin))
		)
	)
)
