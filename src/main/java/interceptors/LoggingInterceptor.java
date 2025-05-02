package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggedInvocation
public class LoggingInterceptor implements Serializable {
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext ctx) throws Exception {
        System.out.println("[LoggingInterceptor] invoked method: " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
