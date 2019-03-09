<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SelfMessage.aspx.cs" Inherits="WebApplication1.SelfMessage" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="返回" />
            <br />
            <asp:GridView ID="GridView1" runat="server" CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False" OnRowCommand="GridView1_RowCommand">
                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                <Columns>
                    <asp:BoundField DataField="id" HeaderText="账号" />
                    <asp:BoundField DataField="password" HeaderText="密码" />
                    <asp:BoundField DataField="name" HeaderText="用户名" />
                    <asp:BoundField DataField="tag" HeaderText="用户类型" />
                    <asp:BoundField DataField="sum" HeaderText="借书总次数" />
                    <asp:BoundField DataField="borrowed" HeaderText="未还书数目" />
                    <asp:BoundField DataField="returned" HeaderText="已还书数目" />
                    <asp:BoundField DataField="credit" HeaderText="信用记录" />
                    <asp:BoundField DataField="capacity" HeaderText="借书总数上限" />
                    <asp:BoundField DataField="leftsum" HeaderText="剩余借书数目" />
                    <asp:BoundField DataField="own" HeaderText="欠款情况" />
                    <asp:ButtonField ButtonType="Button" HeaderText="用户升级" Text="开通会员" CommandName="upgrace" />
                    <asp:ButtonField ButtonType="Button" HeaderText="缴纳罚款" Text="前往缴纳" CommandName="payfee" />
                </Columns>
                <EditRowStyle BackColor="#999999" />
                <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                <SortedAscendingCellStyle BackColor="#E9E7E2" />
                <SortedAscendingHeaderStyle BackColor="#506C8C" />
                <SortedDescendingCellStyle BackColor="#FFFDF8" />
                <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
            </asp:GridView>
        </div>
    </form>
</body>
</html>
