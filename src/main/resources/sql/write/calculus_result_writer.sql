MERGE INTO DBO.calculus_result
USING (VALUES (:id,
               :loanId,
               :debt,
               :feePercentage,
               :fee,
               :calculatedDebt))
    AS source (id,
               loanId,
               debt,
               feePercentage,
               fee,
               calculatedDebt)
ON DBO.calculus_result.id = source.id
WHEN MATCHED THEN
    UPDATE
    SET DBO.calculus_result.loan_id         = source.loanId,
        DBO.calculus_result.debt            = source.debt,
        DBO.calculus_result.fee_percentage  = source.feePercentage,
        DBO.calculus_result.fee             = source.fee,
        DBO.calculus_result.calculated_debt = source.calculatedDebt
WHEN NOT MATCHED THEN
    INSERT (loan_id,
            debt,
            fee_percentage,
            fee,
            calculated_debt)
    VALUES (source.loanId,
            source.debt,
            source.feePercentage,
            source.fee,
            source.calculatedDebt);