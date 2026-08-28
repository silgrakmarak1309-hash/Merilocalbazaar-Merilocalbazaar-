// Meri Local Bazaar - Official Website & APK Download Portal Logic

const DEFAULT_APK_URL = "meri_local_bazaar.apk";
const GITHUB_RELEASE_VIEW_URL = "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/tag/v1.0.0";

let currentApkUrl = DEFAULT_APK_URL;

function updateDownloadUrls() {
  const headerBtn = document.getElementById("headerDownloadBtn");
  const mainBtn = document.getElementById("mainDownloadBtn");
  const installCtaBtn = document.querySelector(".install-cta-btn");

  if (headerBtn) {
    headerBtn.href = currentApkUrl;
    headerBtn.setAttribute("download", "meri_local_bazaar.apk");
  }
  if (mainBtn) {
    mainBtn.href = currentApkUrl;
    mainBtn.setAttribute("download", "meri_local_bazaar.apk");
  }
  if (installCtaBtn) {
    installCtaBtn.href = currentApkUrl;
    installCtaBtn.setAttribute("download", "meri_local_bazaar.apk");
  }
}

function showToast(message) {
  const toast = document.getElementById("toast");
  if (!toast) return;
  toast.textContent = message;
  toast.classList.add("show");
  setTimeout(() => {
    toast.classList.remove("show");
  }, 2800);
}

// Copy Download Link
document.getElementById("btnCopyLink")?.addEventListener("click", () => {
  const fullUrl = window.location.origin ? (window.location.origin + "/meri_local_bazaar.apk") : currentApkUrl;
  navigator.clipboard.writeText(fullUrl).then(() => {
    showToast("APK Download link copied to clipboard!");
  }).catch(() => {
    showToast("Download URL: " + fullUrl);
  });
});

// Gallery Tabs
const galleryTabs = document.querySelectorAll(".gallery-tab");
const galleryPanels = document.querySelectorAll(".gallery-panel");

galleryTabs.forEach((tab) => {
  tab.addEventListener("click", () => {
    galleryTabs.forEach((t) => t.classList.remove("active"));
    galleryPanels.forEach((p) => p.classList.remove("active"));

    tab.classList.add("active");
    const targetId = tab.getAttribute("data-tab");
    document.getElementById(targetId)?.classList.add("active");
  });
});

// FAQ Accordions
const faqItems = document.querySelectorAll(".faq-item");

faqItems.forEach((item) => {
  const questionBtn = item.querySelector(".faq-question");
  questionBtn?.addEventListener("click", () => {
    const isOpen = item.classList.contains("open");
    faqItems.forEach((f) => f.classList.remove("open"));
    if (!isOpen) {
      item.classList.add("open");
    }
  });
});

// Initial Setup
updateDownloadUrls();
