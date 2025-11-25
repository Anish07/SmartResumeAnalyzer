import { Injectable, signal } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ThemeService {
    darkMode = signal<boolean>(false);

    constructor() {
        const savedTheme = localStorage.getItem('theme');
        if (savedTheme === 'dark') {
            this.setDarkMode(true);
        } else if (!savedTheme && window.matchMedia('(prefers-color-scheme: dark)').matches) {
            this.setDarkMode(true);
        }
    }

    toggleTheme() {
        this.setDarkMode(!this.darkMode());
    }

    private setDarkMode(isDark: boolean) {
        this.darkMode.set(isDark);
        if (isDark) {
            document.documentElement.setAttribute('data-theme', 'dark');
            localStorage.setItem('theme', 'dark');
        } else {
            document.documentElement.removeAttribute('data-theme');
            localStorage.setItem('theme', 'light');
        }
    }
}
