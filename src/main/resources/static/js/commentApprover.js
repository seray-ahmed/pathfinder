window.addEventListener("load", () => {
    let buttons = document.querySelectorAll(".card-body > a:nth-child(odd)");
    buttons.forEach(x => x.addEventListener("click", async (e) => {
        e.preventDefault();
        const currentTarget = e.currentTarget;
        const id = e.currentTarget.href.split("/")[e.currentTarget.href.split("/").length - 1];
        const result = await approveComment(id);
        if (result){
            const nodeToDelete = currentTarget.parentNode.parentNode;
            document.querySelector(".wrapper").removeChild(nodeToDelete);
        }
    }))
})
async function approveComment(id){
    const URL = `http://localhost:8080/api/comment/approver/${id}`;
    const response = await fetch(URL);
    const result = response.status;
    return result === 200;
}
