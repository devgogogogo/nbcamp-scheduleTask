//package hello.schedule2.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.PatternMatchUtils;
//
//import java.io.IOException;
//
//@Slf4j
//public class LoginFilter implements Filter {
//
//    //(로그인 하지 않나도될 URL? ) 인증을 하지 않아도 될 URL 이라는데 어떤 URL을 넣어야 하는지
//    private static final String[] WHITE_LIST = {"/members/signup" };
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain)
//            throws IOException, ServletException {
//
//        //다운캐스팅을 해서 -->> 다양한 기능을 더 사용할수 있다.
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        String requestURI = httpRequest.getRequestURI();
//
//        //  만약 URL이 포함되어 있지 않으면? -->필터를 실행해라
//
//        //todo 로그인을 체크 해야하는 URL인지 검사한다.
//        if (!isWhiteList(requestURI)) {
//
//            // 로그인 확인 -> 로그인하면 session에 값이 저장되어 있다는 가정.
//            // 세션이 존재하면 가져온다. 세션이 없으면 session = null
//            HttpSession session = httpRequest.getSession(false);
//
//            //만약 null값이라면 ? 로그인하지 않은 경우다
//            if (session == null || session.getAttribute("sessionKey") == null) {
//                throw new RuntimeException("로그인 해주세요.");
//            }
//            log.info("로그인된 사용자 요청: {}" , requestURI);
//
//        }
//        // 다음 필터로 -->>> 없으면 다음 필터 없으면 Servlet -> Controller 호출
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private boolean isWhiteList(String requestURL) {
//
//        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURL);
//    }
//}
