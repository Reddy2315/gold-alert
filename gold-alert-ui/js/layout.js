async function loadLayout() {
  // Load navbar
  const navHtml = await fetch("partials/navbar.html").then(r => r.text());
  document.getElementById("navbar").innerHTML = navHtml;

  // Load footer
  const footerHtml = await fetch("partials/footer.html").then(r => r.text());
  document.getElementById("footer").innerHTML = footerHtml;

  //  call AFTER navbar is inserted
  setTimeout(showLoggedInUser, 0);
}

function showLoggedInUser() {
  const token = localStorage.getItem("token");
  if (!token) return;

  try {
    const payloadBase64 = token.split(".")[1];

    // Handle Base64URL
    const base64 = payloadBase64
      .replace(/-/g, "+")
      .replace(/_/g, "/");

    const payload = JSON.parse(atob(base64));

    const email = payload.sub;

    const userSpan = document.getElementById("loggedUser");
    if (userSpan && email) {
      userSpan.textContent = email;
    }

  } catch (err) {
    console.error("Failed to decode JWT", err);
  }
}

function logout() {
  showToast("Logged out successfully", "success");

  setTimeout(() => {
    localStorage.removeItem("token");
    window.location.href = "index.html";
  }, 1500); // 1.5 sec delay to show toast
}