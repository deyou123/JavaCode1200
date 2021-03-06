<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
 <body>
   <table>
     <tr bgcolor="#8E8E8E">
       <td>名称</td>
       <td>出版社</td>
       <td>出版公司</td>
       <td>作者</td>
       <td>ISBN号</td>
       <td>价格</td>
     </tr>
   <xsl:for-each select="books/book">
     <tr bgcolor="#C2C287">
       <td><xsl:value-of select="name"/></td>
       <td><xsl:value-of select="publisher"/></td>
       <td><xsl:value-of select="company"/></td>
       <td><xsl:value-of select="author"/></td>
       <td><xsl:value-of select="ISBN"/></td>
       <td><xsl:value-of select="price"/></td>
     </tr>
    </xsl:for-each> 
   <xsl:for-each select="document('document_ext_demo.xml')/books/book">
     <tr bgcolor="#C2C287">
       <td><xsl:value-of select="name"/></td>
       <td><xsl:value-of select="publisher"/></td>
       <td><xsl:value-of select="company"/></td>
       <td><xsl:value-of select="author"/></td>
       <td><xsl:value-of select="ISBN"/></td>
       <td><xsl:value-of select="price"/></td>
     </tr>
    </xsl:for-each> 
   </table>
 </body>
 </html>
</xsl:template>
</xsl:stylesheet>
