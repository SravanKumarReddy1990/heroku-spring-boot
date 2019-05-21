<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
   <head>
      <meta charset="UTF-8"></meta>
      <title th:utext="#{label.title}"></title>
   </head>
   <body>
      <div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
         <a th:href="@{/login1?language=en}">Login (English)</a>
         &nbsp;|&nbsp;
         <a th:href="@{/login1?language=tel}">Login (Telugu)</a>
       &nbsp;|&nbsp;
       <a th:href="@{/login1?language=hi}">Login (Hindhi)</a>
      </div>
      <form method="post" action="">
         <table>
            <tr>
               <td>
                  <strong th:utext="#{label.userName}"></strong>
               </td>
               <td><input name="userName" /></td>
            </tr>
            <tr>
               <td>
                  <strong  th:utext="#{label.password}"></strong>
               </td>
               <td><input name="password" /></td>
            </tr>
            <tr>
               <td colspan="2">
                  <input type="submit" th:value="#{label.submit}" />
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>