MERGE INTO DBO.calculus_rule
USING (VALUES (:id,
               :loanId,
               :ruleId))
    AS source (id,
               loanId,
               ruleId)
ON DBO.calculus_rule.id = source.id
WHEN MATCHED THEN
    UPDATE
    SET DBO.calculus_rule.loan_id = source.loanId,
        DBO.calculus_rule.rule_id = source.ruleId
WHEN NOT MATCHED THEN
    INSERT (loan_id,
            rule_id)
    VALUES (source.loanId,
            source.ruleId);