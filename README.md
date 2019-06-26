# Sping Boot  + AOP 
---
##### 本项目实现了对异常的AOP处理，以及对异常的全局分装处理，（现实情况我们使用AOP是来日志安全的记录)

---
### 使用的注解
1. `@RestControllerAdvice` 开启全局异常处理
2. `@ExceptionHandler` 在全局管理异常处理下，对指定的有异常进行处理
3. `@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")` 数据json化的时候，将其转成这样的格式