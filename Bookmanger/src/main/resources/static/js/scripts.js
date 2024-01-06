/*!
* 부트스트랩 시작 - 단순 사이드바 v6.0.6 (https://startbootstrap.com/template/simple-sidebar)
* 저작권 2013-2023 부트스트랩 시작
* MIT 라이선스(https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
//
// 스크립트
//

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // 새로 고침 사이에 사이드바 토글을 유지하려면 아래의 주석 처리를 제거하세요
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});
