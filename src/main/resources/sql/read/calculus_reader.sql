SELECT CRE.id       as CALCULUS_ID,
       ED.loan_id   as LOAN_ID,
       ED.debt      AS DEBT,
       P.percentage AS PERCENTAGE
FROM external_data ED
         OUTER APPLY (SELECT *
                      FROM DBO.calculus_rule CR
                      WHERE CR.loan_id = ED.loan_id) CR
         OUTER APPLY (SELECT *
                      FROM DBO.[rule] R
                      WHERE R.id = CR.rule_id) R
         OUTER APPLY (SELECT *
                      FROM DBO.percentage P
                      WHERE P.id = R.percentage_id) P
         OUTER APPLY (SELECT *
                      FROM DBO.calculus C
                      where C.loan_id = ED.loan_id) CRE
WHERE ED.bank_id = ${bank_id}