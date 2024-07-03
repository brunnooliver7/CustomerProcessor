MERGE INTO DBO.loan_rule
USING (VALUES (:id,
               :loanId,
               :calculusRuleId))
    AS source (id,
               loanId,
               calculusRuleId)
ON DBO.loan_rule.id = source.id
WHEN MATCHED THEN
    UPDATE
    SET DBO.loan_rule.loan_id          = source.loanId,
        DBO.loan_rule.calculus_rule_id = source.calculusRuleId
WHEN NOT MATCHED THEN
    INSERT (loan_id,
            calculus_rule_id)
    VALUES (source.loanId,
            source.calculusRuleId);