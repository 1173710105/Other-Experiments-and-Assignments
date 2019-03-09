<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="payform.aspx.cs" Inherits="WebApplication1.payform" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>支付页</title>
</head>
<body>
    <form id="form1" runat="server" >
        <div align ="center">
            <asp:Panel ID="Panel1" runat="server" BackColor="#66FFFF" BorderColor="#66FFFF" Height="600px">
                <div align ="center">
                    <asp:Label ID="label1" runat="server" Font-Names="微软雅黑" Font-Size="Larger" Text="微信"></asp:Label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <asp:Label ID="label2" runat="server" Font-Italic="False" Font-Names="微软雅黑" Font-Size="Larger" Font-Underline="False" Text="支付宝"></asp:Label>
                    <br />
                    <br />
                    <asp:Image ID="Image1" runat="server" Height="300px" ImageUrl="~/image/微信.jpg" Width="300px" />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<asp:Image ID="Image2" runat="server" Height="300px" ImageUrl="~/image/支付宝.jpg" Width="300px" />
                    <br />
                    <asp:Label ID="Label3" runat="server" Font-Names="微软雅黑" Text="Label"></asp:Label>
                    <br />
                    &nbsp;<br /> <asp:Button ID="Button1" runat="server" BackColor="White" Font-Names="微软雅黑" Font-Size="Large" Height="46px" OnClick="Button1_Click" Text="完成支付" Width="136px" />
                </div>
            </asp:Panel>
        &nbsp;&nbsp;&nbsp;
            </div>
    </form>
</body>
</html>
