SELECT ED.loan_id AS ID_LOAN,
       CR.id      AS ID_CALCULUS_RULE,
       LR.id      AS ID_LOAN_RULE
FROM DBO.external_data ED
         OUTER APPLY (SELECT *
                      FROM DBO.debt_delay DD
                      WHERE DD.min <= ED.delay
                        AND DD.max >= ED.delay) DD
         OUTER APPLY (SELECT *
                      FROM DBO.risk R
                      WHERE R.min_revenue <= ED.revenue_amount
                        AND R.max_revenue > ED.revenue_amount) R
         OUTER APPLY (SELECT *
                      FROM DBO.calculus_rule CR
                      WHERE CR.id_debt_delay = DD.id
                        AND CR.id_risk = R.id) CR
         OUTER APPLY (SELECT *
                      FROM DBO.percentage P
                      WHERE P.id = CR.id_percentage) P
         OUTER APPLY (SELECT *
                      FROM DBO.loan_rule LR
                      WHERE LR.id = ED.loan_id) LR