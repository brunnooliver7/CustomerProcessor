package com.bruno.customerprocessor.service

import com.bruno.customerprocessor.entity.domain.DebtDelay
import com.bruno.customerprocessor.entity.domain.Percentage
import com.bruno.customerprocessor.entity.domain.Risk
import com.bruno.customerprocessor.entity.domain.CalculusRule
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class DomainDataService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private val logger: Logger = LoggerFactory.getLogger(DomainDataService::class.java)

    @Transactional
    fun generatePercentages() {
        logger.info("Generating Percentages")

        val p1 = Percentage(percentage = BigDecimal(0))
        val p2 = Percentage(percentage = BigDecimal(5))
        val p3 = Percentage(percentage = BigDecimal(10))
        val p4 = Percentage(percentage = BigDecimal(20))
        val p5 = Percentage(percentage = BigDecimal(50))

        val percentages = listOf(p1, p2, p3, p4, p5)

        persistEntities(percentages)
    }

    @Transactional
    fun generateDebtDelays() {
        logger.info("Generating Debt Delays")

        val dd1 = DebtDelay(min = 0, max = 6)
        val dd2 = DebtDelay(min = 7, max = 14)
        val dd3 = DebtDelay(min = 15, max = 30)
        val dd4 = DebtDelay(min = 31, max = 90)
        val dd5 = DebtDelay(min = 91, max = Int.MAX_VALUE)

        val debtDelays = listOf(dd1, dd2, dd3, dd4, dd5)

        persistEntities(debtDelays)
    }

    @Transactional
    fun generateRisks() {
        logger.info("Generating Risks")

        val r1 = Risk(risk = "A", minRevenue = BigDecimal(12_000), maxRevenue = BigDecimal(10E12))
        val r2 = Risk(risk = "B", minRevenue = BigDecimal(8_000), maxRevenue = BigDecimal(12_000))
        val r3 = Risk(risk = "C", minRevenue = BigDecimal(5_000), maxRevenue = BigDecimal(8_000))
        val r4 = Risk(risk = "D", minRevenue = BigDecimal(3_000), maxRevenue = BigDecimal(5_000))
        val r5 = Risk(risk = "E", minRevenue = BigDecimal(0), maxRevenue = BigDecimal(3_000))

        val risks = listOf(r1, r2, r3, r4, r5)

        persistEntities(risks)
    }

    @Transactional
    fun generateRules() {
        logger.info("Generating Rules")

        val calculusRule1 = CalculusRule(debtDelayId = 1, riskId = 1, percentageId = 1)
        val calculusRule2 = CalculusRule(debtDelayId = 1, riskId = 2, percentageId = 1)
        val calculusRule3 = CalculusRule(debtDelayId = 1, riskId = 3, percentageId = 2)
        val calculusRule4 = CalculusRule(debtDelayId = 1, riskId = 4, percentageId = 2)
        val calculusRule5 = CalculusRule(debtDelayId = 1, riskId = 5, percentageId = 3)
        val calculusRule6 = CalculusRule(debtDelayId = 2, riskId = 1, percentageId = 1)
        val calculusRule7 = CalculusRule(debtDelayId = 2, riskId = 2, percentageId = 2)
        val calculusRule8 = CalculusRule(debtDelayId = 2, riskId = 3, percentageId = 2)
        val calculusRule9 = CalculusRule(debtDelayId = 2, riskId = 4, percentageId = 3)
        val calculusRule10 = CalculusRule(debtDelayId = 2, riskId = 5, percentageId = 4)
        val calculusRule11 = CalculusRule(debtDelayId = 3, riskId = 1, percentageId = 2)
        val calculusRule12 = CalculusRule(debtDelayId = 3, riskId = 2, percentageId = 2)
        val calculusRule13 = CalculusRule(debtDelayId = 3, riskId = 3, percentageId = 3)
        val calculusRule14 = CalculusRule(debtDelayId = 3, riskId = 4, percentageId = 4)
        val calculusRule15 = CalculusRule(debtDelayId = 3, riskId = 5, percentageId = 4)
        val calculusRule16 = CalculusRule(debtDelayId = 4, riskId = 1, percentageId = 2)
        val calculusRule17 = CalculusRule(debtDelayId = 4, riskId = 2, percentageId = 3)
        val calculusRule18 = CalculusRule(debtDelayId = 4, riskId = 3, percentageId = 4)
        val calculusRule19 = CalculusRule(debtDelayId = 4, riskId = 4, percentageId = 4)
        val calculusRule20 = CalculusRule(debtDelayId = 4, riskId = 5, percentageId = 5)
        val calculusRule21 = CalculusRule(debtDelayId = 5, riskId = 1, percentageId = 3)
        val calculusRule22 = CalculusRule(debtDelayId = 5, riskId = 2, percentageId = 4)
        val calculusRule23 = CalculusRule(debtDelayId = 5, riskId = 3, percentageId = 4)
        val calculusRule24 = CalculusRule(debtDelayId = 5, riskId = 4, percentageId = 5)
        val calculusRule25 = CalculusRule(debtDelayId = 5, riskId = 5, percentageId = 5)

        val rules = listOf(
            calculusRule1, calculusRule2, calculusRule3, calculusRule4, calculusRule5,
            calculusRule6, calculusRule7, calculusRule8, calculusRule9, calculusRule10,
            calculusRule11, calculusRule12, calculusRule13, calculusRule14, calculusRule15,
            calculusRule16, calculusRule17, calculusRule18, calculusRule19, calculusRule20,
            calculusRule21, calculusRule22, calculusRule23, calculusRule24, calculusRule25,
        )

        persistEntities(rules)
    }

    private fun <T> persistEntities(entities: List<T>) {
        entities.forEach { entity ->
            if (entityManager.contains(entity)) {
                entityManager.merge(entity)
            } else {
                entityManager.persist(entity)
            }
        }

        entityManager.flush()
        entityManager.clear()
    }

}