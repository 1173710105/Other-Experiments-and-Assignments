<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MyCollection.aspx.cs" Inherits="WebApplication1.MyCollection" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        &nbsp;<asp:Button ID="Button1" runat="server" Height="31px" OnClick="Button1_Click" Text="返回" Width="90px" />
            <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" CellPadding="4" ForeColor="#333333" GridLines="None" OnRowCommand="GridView1_RowCommand" OnRowDeleting="GridView1_RowDeleting">
                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                <Columns>
                    <asp:BoundField DataField="bookid" HeaderText="图书编号" />
                    <asp:BoundField DataField="collectionid" HeaderText="收藏编号" />
                    <asp:BoundField DataField="name" HeaderText="书名" />
                    <asp:BoundField DataField="message" HeaderText="图书信息" />
                    <asp:BoundField DataField="class" HeaderText="图书类别" />
                    <asp:ButtonField ButtonType="Button" CommandName="borrow" HeaderText="前往借阅" Text="前往借阅" />
                    <asp:ButtonField ButtonType="Button" CommandName="delete" HeaderText="删除收藏" Text="删除">
                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                    </asp:ButtonField>
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
