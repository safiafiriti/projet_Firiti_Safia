package org.formation.simplecashsi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.formation.simplecashsi.dto.VirementRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class VirementTracingAspect {

    private static final Logger VIREMENT_LOGGER =
            LoggerFactory.getLogger("VIREMENT_LOGGER");

    private final String transferExecution =
            "execution(* org.formation.simplecashsi.service.TransactionService.effectuerVirement(..)) && args(request)";

    @AfterReturning(
            pointcut = transferExecution,
            argNames = "joinPoint,request")
    public void logVirementSuccess(JoinPoint joinPoint, VirementRequestDto request) {

        String message = "SUCCESS | FROM_ID=" + request.compteDebiteurId() +
                " | TO_ID=" + request.compteCrediteurId() +
                " | AMOUNT=" + request.montant();

        VIREMENT_LOGGER.info(message);
    }
}