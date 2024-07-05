SELECT ED.loan_id AS LOAN_ID,
       CR.id      AS CALCULUS_RULE_ID,
       RU.id      AS RULE_ID
FROM DBO.external_data ED
         OUTER APPLY (SELECT *
                      FROM DBO.debt_delay DD
                      WHERE DD.min <= ED.delay
                        AND DD.max >= ED.delay) DD
         OUTER APPLY (SELECT *
                      FROM DBO.risk RS
                      WHERE RS.min_revenue <= ED.revenue_amount
                        AND RS.max_revenue > ED.revenue_amount) RS
         OUTER APPLY (SELECT *
                      FROM DBO.[rule] R
                      WHERE R.debt_delay_id = DD.id
                        AND R.risk_id = RS.id) RU
         OUTER APPLY (SELECT *
                      FROM DBO.percentage P
                      WHERE P.id = RU.percentage_id) P
         OUTER APPLY (SELECT *
                      FROM DBO.calculus_rule CR
                      WHERE CR.id = ED.loan_id) CR