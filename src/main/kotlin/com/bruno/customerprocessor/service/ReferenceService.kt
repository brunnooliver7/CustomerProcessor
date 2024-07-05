package com.bruno.customerprocessor.service

import com.bruno.customerprocessor.entity.reference.DebtDelay
import com.bruno.customerprocessor.entity.reference.Percentage
import com.bruno.customerprocessor.entity.reference.Risk
import com.bruno.customerprocessor.entity.reference.Rule
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ReferenceService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private val logger: Logger = LoggerFactory.getLogger(ReferenceService::class.java)

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

        val rule1 = Rule(debtDelayId = 1, riskId = 1, percentageId = 1)
        val rule2 = Rule(debtDelayId = 1, riskId = 2, percentageId = 1)
        val rule3 = Rule(debtDelayId = 1, riskId = 3, percentageId = 2)
        val rule4 = Rule(debtDelayId = 1, riskId = 4, percentageId = 2)
        val rule5 = Rule(debtDelayId = 1, riskId = 5, percentageId = 3)
        val rule6 = Rule(debtDelayId = 2, riskId = 1, percentageId = 1)
        val rule7 = Rule(debtDelayId = 2, riskId = 2, percentageId = 2)
        val rule8 = Rule(debtDelayId = 2, riskId = 3, percentageId = 2)
        val rule9 = Rule(debtDelayId = 2, riskId = 4, percentageId = 3)
        val rule10 = Rule(debtDelayId = 2, riskId = 5, percentageId = 4)
        val rule11 = Rule(debtDelayId = 3, riskId = 1, percentageId = 2)
        val rule12 = Rule(debtDelayId = 3, riskId = 2, percentageId = 2)
        val rule13 = Rule(debtDelayId = 3, riskId = 3, percentageId = 3)
        val rule14 = Rule(debtDelayId = 3, riskId = 4, percentageId = 4)
        val rule15 = Rule(debtDelayId = 3, riskId = 5, percentageId = 4)
        val rule16 = Rule(debtDelayId = 4, riskId = 1, percentageId = 2)
        val rule17 = Rule(debtDelayId = 4, riskId = 2, percentageId = 3)
        val rule18 = Rule(debtDelayId = 4, riskId = 3, percentageId = 4)
        val rule19 = Rule(debtDelayId = 4, riskId = 4, percentageId = 4)
        val rule20 = Rule(debtDelayId = 4, riskId = 5, percentageId = 5)
        val rule21 = Rule(debtDelayId = 5, riskId = 1, percentageId = 3)
        val rule22 = Rule(debtDelayId = 5, riskId = 2, percentageId = 4)
        val rule23 = Rule(debtDelayId = 5, riskId = 3, percentageId = 4)
        val rule24 = Rule(debtDelayId = 5, riskId = 4, percentageId = 5)
        val rule25 = Rule(debtDelayId = 5, riskId = 5, percentageId = 5)

        val rules = listOf(
            rule1, rule2, rule3, rule4, rule5,
            rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15,
            rule16, rule17, rule18, rule19, rule20,
            rule21, rule22, rule23, rule24, rule25,
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