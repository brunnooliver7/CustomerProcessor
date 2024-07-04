SELECT CRE.id       as ID_CALCULUS_RESULT,
       ED.loan_id   as ID_LOAN,
       ED.debt      AS DEBT,
       P.percentage AS PERCENTAGE
FROM external_data ED
         OUTER APPLY (SELECT *
                      FROM loan_rule LR
                      WHERE LR.loan_id = ED.loan_id) LR
         OUTER APPLY (SELECT *
                      FROM calculus_rule CR
                      WHERE CR.id = LR.calculus_rule_id) CR
         OUTER APPLY (SELECT *
                      FROM percentage P
                      WHERE P.id = CR.id_percentage) P
         OUTER APPLY (SELECT *
                      FROM calculus_result CRE
                      where CRE.loan_id = ED.loan_id) CRE