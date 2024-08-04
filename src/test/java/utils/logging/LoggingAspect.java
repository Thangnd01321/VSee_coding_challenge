package utils.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
public class LoggingAspect {

  private LocalDateTime beforeActionTime;

  @Before("execution(* selenium.core.BasePage+.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    StringBuilder builder = new StringBuilder();
    for (Object value : args) {
      builder.append("\"").append(value.toString()).append("\", ");
    }
    if (builder.toString().endsWith(", ")) {
      builder.delete(builder.length() - 2, builder.length());
    }
    Signature signature = joinPoint.getSignature();
    if (builder.length() == 0) {
      Log.action(
          "Starting action: "
              + signature.getDeclaringTypeName()
              + "."
              + signature.getName()
              + "()");
    } else {
      Log.action(
          "Starting action: "
              + signature.getDeclaringTypeName()
              + "."
              + signature.getName()
              + "("
              + builder
              + ")");
    }
    beforeActionTime = LocalDateTime.now();
  }

  @AfterThrowing(pointcut = "execution(* selenium.core.BasePage+.*(..))", throwing = "error")
  public void afterFailureAction(JoinPoint joinPoint, Throwable error) throws IOException {
    String actionDuration = elapseDuration(beforeActionTime);
    Log.action("Action failed: " + error + ". (" + actionDuration + " sec).");
  }

  @AfterReturning(pointcut = "execution(* selenium.core.BasePage+.*(..))", returning = "result")
  public void afterAction(JoinPoint joinPoint, Object result) throws IOException {
    String actionDuration = elapseDuration(beforeActionTime);
    Log.action("Action Done! (" + actionDuration + " sec).");
  }

  public String elapseDuration(LocalDateTime before) {
    LocalDateTime after = LocalDateTime.now();
    Duration d = Duration.between(before, after);
    return String.valueOf((float) d.toMillis() / 1000);
  }
}
