package com.bruno.customerprocessor.batch.tasklet

import com.bruno.customerprocessor.entity.external.Bank
import io.github.serpro69.kfaker.Faker
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class GenerateBanksTasklet() : Tasklet {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val faker = Faker()

        faker.unique.configuration {
            enable(faker::bank)
        }

        val banks = mutableListOf<Bank>()

        for (i in 1..7) {
            val bank = Bank(
                bankName = faker.bank.name()
            )

            entityManager.persist(bank)

            banks.add(bank)

            entityManager.flush()
            entityManager.clear()
        }

        return RepeatStatus.FINISHED
    }

}