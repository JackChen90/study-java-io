package study.java.io.uniq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Pattern;

/**
 * @author jackie chen
 * @create 2017/10/19
 * @description TestUniqApi
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class UniqApiTest {

	@Autowired
	UniqApi uniqApi;

	@Test
	public void uniqApiTest() {
		uniqApi.uniqApi();
	}

	@Test
	public void regexTest() {
		String requestURI ="/battleservice/user/updateUserInfo";
		String requestURLSTR = "http://cmsnspre.cmgame.com/battleservice/user/updateUserInfo";
		StringBuffer requestURL = new StringBuffer(requestURLSTR);

		String tempContextUrl = requestURL.delete(requestURL.length() -
				requestURI.length(), requestURL.length()).toString();

		System.out.println(tempContextUrl);

		String regex = "^(http://|https://)((pre|test).*\\..*|[^.]*(pre|test).*)";
		System.out.println(regex);

		boolean flag = Pattern.matches(regex, "http://cmsnspre.cmgame.com");
		System.out.println(flag);
	}
}
