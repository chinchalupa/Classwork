(define max-edges
	(lambda (n)
		(/ (* n (- n 1)) 2)
	)
)

(define checkall
	(lambda (check ls)
		(if (null? check)
			#t
			(if (member (car check) ls)
				(checkall (cdr check) ls)
				#f
			)
		)
	)
)

(define complete?
	(lambda (g)
		(define allelements (map car g))
		
		(andmap (lambda (val useless)
			(ormap (lambda (check gtemp)
				(checkall check (cadr gtemp)))
			
				(make-list (length g) (cadr val))
				g)
			)
			(complete (map car g))
			g)
		)
)




(define find-if-all-members
	(lambda (allelements g)
		(if (null? g)
			#t
			(and 
				(andmap member (cons (cadr g) (caar g)) (allelements))
				(find-if-all-members allelements (cdr g))
			)
			
		)
	)
)

(define complete
	(lambda (elements)
		(map (lambda (element useless)
			(list element (remove element elements)))
			elements elements) 
	)
)

(define replace
	(lambda (var repl ls)
		(map (lambda (element elements)
			(if (eq? element var)
				repl
				element
			)
		) ls ls)
	)
)

(define remove-first
	(lambda (element ls)
		(find-item element ls)
	)
)

(define find-item
	(lambda (element ls)
		(if (null? ls)
			ls
			(if (eq? element (car ls))
				(cdr ls)
				(apply list (car ls) (find-item element (cdr ls)))
			)
		)
	)
)

(define (reversels l)
  (if (null? l)
     '()
     (append (reversels (cdr l)) (list (car l)))
  )
)

(define remove-last
	(lambda (element ls)
		(define newls (reversels ls))
		(define removedls (remove-first element newls))
		(reversels removedls)
	)
)