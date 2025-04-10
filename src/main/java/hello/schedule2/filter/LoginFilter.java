package hello.schedule2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    // 인증을 하지 않아도될 URL Path 배열
    private static final String[] WHITE_LIST = {"/", "/user/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅 해야함
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        //다양한 기능을 사용하기 위해 다운 캐스팅 해야함
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 포함을 하면 session 이 발생해서 if문을 실행안하는건가요 ?
        //WHITE_LIST에 포함되지 않으면 아래 로직 실행!!
        //실행하는 이유는 request요청을 할때마다 확인해야하니깐 ?
        if (!isWhiteList(requestURI)) {

            //로그인 확인 하고 -->> 로그인하면 session에 값이 저장되어 있다는 가정.
            //세전이 존재하면 가져온다. 세션이 없으면 session = null
            HttpSession session = httpRequest.getSession(false);

            //로그인하지 않은 사용용자인 경우에는
            if (session != null || session.getAttribute("sessionKey") == null) {
                throw new RuntimeException("로그인 해주세요");
            }

            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");
        }
        // 1번경우 : whiteListURL에 등록된 URL 요청이면 바로 chain.doFilter()
        // 2번경우 : 필터 로직 통과 후 다음 필터 호출 chain.doFilter()
        // 다음 필터 없으면 Servlet -> Controller 호출

//        WHITE_LIST가 있다면 실행
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        // request URI가 whiteListURL에 포함되는지 확인
        // 포함되면 true 반환
        // 포함되지 않으면 false 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}