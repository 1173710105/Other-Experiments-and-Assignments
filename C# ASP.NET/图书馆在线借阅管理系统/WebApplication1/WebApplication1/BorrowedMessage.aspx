<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="BorrowedMessage.aspx.cs" Inherits="WebApplication1.BorrowedMessage" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Button ID="Button1" runat="server" Text="返回" OnClick="Button1_Click" Width="75px" />
            <asp:GridView ID="GridView1" runat="server" CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False" OnRowCommand="GridView1_RowCommand">
                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                <Columns>
                    <asp:BoundField DataField="bookid" HeaderText="书本编号" />
                    <asp:BoundField DataField="num" HeaderText="借阅编号" />
                    <asp:BoundField DataField="name" HeaderText="书名" />
                    <asp:BoundField DataField="message" HeaderText="书本信息" />
                    <asp:BoundField DataField="datalend" HeaderText="借书日期" />
                    <asp:BoundField DataField="deadline" HeaderText="到期日期" />
                    <asp:BoundField DataField="datareturn" HeaderText="归还日期" />
                    <asp:BoundField DataField="returned" HeaderText="借阅状态" >
                    <ControlStyle ForeColor="Red" />
                    <FooterStyle ForeColor="Red" />
                    </asp:BoundField>
                    <asp:BoundField DataField="id" HeaderText="借阅者账号" />
                    <asp:BoundField DataField="count" HeaderText="借阅者" />
                    <asp:ButtonField ButtonType="Button" CommandName="ContinueBorrow" HeaderText="续借" Text="续借" />
                    <asp:ButtonField ButtonType="Button" HeaderText="归还" Text="归还" CommandName="Return" />
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
