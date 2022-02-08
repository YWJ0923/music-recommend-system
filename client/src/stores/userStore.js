import {makeObservable, observable, action} from 'mobx';

class UserStore {
    user = undefined;

    constructor() {
        makeObservable(this, {
            user: observable,
            setUser: action
        })
    }

    setUser = (user) => {
        this.user = user;
    }
}

export const userStore = new UserStore();