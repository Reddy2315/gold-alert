function showToast(message, type = "success", options = {}) {
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

  // 🔹 FORCE SINGLE LINE LAYOUT
  toastBody.className = "toast-body d-flex align-items-center";

  // clear old content
  toastBody.innerHTML = "";

  // message text
  const msgSpan = document.createElement("span");
  msgSpan.className = "me-2";
  msgSpan.innerText = message;
  toastBody.appendChild(msgSpan);

  // optional action button
  if (options.actionText && options.actionUrl) {
    const btn = document.createElement("button");
    btn.className = "btn btn-sm btn-light";
    btn.innerText = options.actionText;

    btn.onclick = () => {
      window.location.href = options.actionUrl;
    };

    toastBody.appendChild(btn);
  }

  const toast = new bootstrap.Toast(toastEl, { delay: 4000 });
  toast.show();
}