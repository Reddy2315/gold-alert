function showToast(message, type = "success") {
  const toastEl = document.getElementById("appToast");
  const toastBody = document.getElementById("toastMessage");

  // reset classes
  toastEl.className = "toast align-items-center border-50";

  if (type === "success") {
    toastEl.classList.add("text-bg-success");
  } else if (type === "error") {
    toastEl.classList.add("text-bg-danger");
  } else if (type === "info") {
    toastEl.classList.add("text-bg-primary");
  }

  toastBody.innerText = message;

  const toast = new bootstrap.Toast(toastEl, { delay: 3000 });
  toast.show();
}