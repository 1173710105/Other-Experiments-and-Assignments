<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SearchBook.aspx.cs" Inherits="WebApplication1.SearchBook" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            &nbsp;<asp:Button ID="Button1" runat="server" Height="30px" OnClick="Button1_Click" Text="返回" Width="90px" />
            &nbsp;
            <asp:DropDownList ID="DropDownList1" runat="server" style="margin-bottom: 0px">
                <asp:ListItem Selected="True">搜索方式</asp:ListItem>
                <asp:ListItem>图书编号</asp:ListItem>
                <asp:ListItem Value="书名">图书书名</asp:ListItem>
                <asp:ListItem Value="类别">图书类别</asp:ListItem>
            </asp:DropDownList>
&nbsp;<asp:DropDownList ID="DropDownList2" runat="server">
                <asp:ListItem Selected="True">图书类别</asp:ListItem>
                <asp:ListItem>所有</asp:ListItem>
                <asp:ListItem>文学类</asp:ListItem>
                <asp:ListItem>工具类</asp:ListItem>
                <asp:ListItem>社科类</asp:ListItem>
                <asp:ListItem>其他</asp:ListItem>
            </asp:DropDownList>
&nbsp;<asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
&nbsp;
            <asp:Button ID="Button2" runat="server" OnClick="Button2_Click" Text="搜索" />
&nbsp;<asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" CellPadding="4" ForeColor="#333333" GridLines="None" OnRowCommand="GridView1_RowCommand">
                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                <Columns>
                    <asp:BoundField DataField="id" HeaderText="图书编号" />
                    <asp:BoundField DataField="name" HeaderText="书名" />
                    <asp:BoundField DataField="message" HeaderText="图书信息" />
                    <asp:BoundField DataField="publisher" HeaderText="出版社" />
                    <asp:BoundField DataField="price" HeaderText="价格" />
                    <asp:BoundField DataField="count" HeaderText="剩余数目" />
                    <asp:BoundField DataField="class" HeaderText="类别" />
                    <asp:ButtonField ButtonType="Button" CommandName="borrow" Text="借阅" HeaderText="借阅" />
                    <asp:ButtonField ButtonType="Button" CommandName="collection" HeaderText="收藏" Text="收藏" />
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
