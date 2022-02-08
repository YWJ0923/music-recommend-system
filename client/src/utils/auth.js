
export function getToken() {
    var token = localStorage.getItem('token');
    return token ? token : sessionStorage.getItem('token');
}

export function removeToken() {
    localStorage.removeItem('token');
    sessionStorage.removeItem('token');
}