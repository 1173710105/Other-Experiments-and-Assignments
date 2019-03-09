<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="WebApplication1.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1"  runat="server">
        <div align="center">
            <asp:Button ID="Button6" runat="server" Height="53px" Text="个人信息总览" Width="173px" OnClick="Button6_Click" />&nbsp;
            <asp:Button ID="Button1" runat="server" Height="53px" Text="借阅情况查询" Width="173px" OnClick="Button1_Click" />&nbsp;
            <asp:Button ID="Button2" runat="server" Height="53px" Text="浏览图书" Width="173px" OnClick="Button2_Click" />&nbsp;
            <asp:Button ID="Button3" runat="server" Height="53px" Text="我的书架" Width="173px" OnClick="Button3_Click" />&nbsp;
            <asp:Button ID="Button4" runat="server" Height="53px" Text="反馈意见" Width="173px" OnClick="Button4_Click" />&nbsp;
            <asp:Button ID="Button8" runat="server" Height="53px" Text="注销" Width="173px" OnClick="Button8_Click" />&nbsp;&nbsp;<br />
        </div>
    </form>
</body>
</html>
