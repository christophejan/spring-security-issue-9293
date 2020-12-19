[minimal sample for spring security issue 9293](https://github.com/spring-projects/spring-security/issues/9293)
===========================================================================================================

## Define :
* a WebSecurityConfigurerAdapter that apply a configurer that use UserDetailsService shared object (through a filter that use UserDetailsService shared object when parameter 'testSharedUserDetailsService' has 'true' value)
* a UserDetailsService bean (without specifying it in WebSecurityConfigurerAdapter)
* a html page

## The issue
The UserDetailsService shared object throw "UserDetailsService is required." when used (call '' url with parameter 'testSharedUserDetailsService' having 'true' value to test).  
the UserDetailsServiceImpl bean has however been found as the login page behavior show (i.e. Login page works as expected, with login=user and password=pwd).

## SpringSecurityIssue9293Test
Contain 3 tests :
* testLogin : to validate that the login page use UserDetailsServiceImpl bean as expected
* testHome : to validate that the home page access with 'user' as expected
* testUsingSharedUserDetailsService : to show the 'IllegalStateException: UserDetailsService is required.' when try to used the UserDetailsService shared object.