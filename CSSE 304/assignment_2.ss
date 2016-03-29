(define fact
	(lambda (n)
		(if (eq? n 0)
			1
			(* n (fact (- n 1)))
		)
	)
)

(define choose
	(lambda (n k)
		(if (eq? n 0)
			1
			(/ (fact n) (* (fact (- n k)) (fact k)))
		)
	)
)

(define range
	(lambda (n k)
		(if (<= k n)
			'()
			(cons n (range(+ 1 n) k))
		)
	)
)

(define set?
	(lambda (ls)
		
		(if (null? ls)
			#t
			(if (member (car ls) (cdr ls))
				#f
				(set? (cdr ls))
			
			)
		)				
	)
)

(define sum-of-squares
	(lambda (ls)
		(if (null? ls)
			0
			(+ (* (car ls) (car ls)) (sum-of-squares (cdr ls)))
		)
	)
)

(define make-vec-from-points
	(lambda (ls1 ls2)
		(if (null? ls1)
			'()
			(cons (- (car ls2) (car ls1)) (make-vec-from-points (cdr ls1) (cdr ls2)))
		)
	)
)

(define dot-product
	(lambda (ls1 ls2)
		(if (null? ls1)
			0
			(+ (* (car ls1) (car ls2)) (dot-product (cdr ls1) (cdr ls2)))
		)
	)
)

(define vec-length
	(lambda (ls)
		(if (null? ls)
			0
			(sqrt (square-list ls))
		)
	)
)

(define square-list
	(lambda (ls)
		(if (null? ls)
			0
			(+ (* (car ls) (car ls)) (square-list (cdr ls)))
		)
	)
)

(define distance
	(lambda (ls1 ls2)
		(vec-length (make-vec-from-points ls1 ls2))
	)
)

(define cross-product
	(lambda (ls1 ls2)
		(list
			(- (* (cadr ls1) (caddr ls2)) (* (caddr ls1) (cadr ls2)))
			(- (* (caddr ls1) (car ls2)) (* (car ls1) (caddr ls2)))
			(- (* (car ls1) (cadr ls2)) (* (cadr ls1) (car ls2)))
		)
	)
)

(define parallel?
	(lambda (ls1 ls2)
		(equal? (inexact->exact (* (vec-length ls1) (vec-length ls2))) (inexact->exact (dot-product ls1 ls2)))
	)
)

(define collinear?
	(lambda (ls1 ls2 ls3)
		(parallel? (make-vec-from-points ls1 ls2) (make-vec-from-points ls1 ls3))
	)
)