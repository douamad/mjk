import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'objet-saisine',
        loadChildren: () => import('./objet-saisine/objet-saisine.module').then(m => m.MjkObjetSaisineModule),
      },
      {
        path: 'origine-saisine',
        loadChildren: () => import('./origine-saisine/origine-saisine.module').then(m => m.MjkOrigineSaisineModule),
      },
      {
        path: 'nature-saisine',
        loadChildren: () => import('./nature-saisine/nature-saisine.module').then(m => m.MjkNatureSaisineModule),
      },
      {
        path: 'conclusion',
        loadChildren: () => import('./conclusion/conclusion.module').then(m => m.MjkConclusionModule),
      },
      {
        path: 'maison',
        loadChildren: () => import('./maison/maison.module').then(m => m.MjkMaisonModule),
      },
      {
        path: 'profession',
        loadChildren: () => import('./profession/profession.module').then(m => m.MjkProfessionModule),
      },
      {
        path: 'objet-assistance',
        loadChildren: () => import('./objet-assistance/objet-assistance.module').then(m => m.MjkObjetAssistanceModule),
      },
      {
        path: 'ethnie',
        loadChildren: () => import('./ethnie/ethnie.module').then(m => m.MjkEthnieModule),
      },
      {
        path: 'saisine',
        loadChildren: () => import('./saisine/saisine.module').then(m => m.MjkSaisineModule),
      },
      {
        path: 'requerant',
        loadChildren: () => import('./requerant/requerant.module').then(m => m.MjkRequerantModule),
      },
      {
        path: 'creance',
        loadChildren: () => import('./creance/creance.module').then(m => m.MjkCreanceModule),
      },
      {
        path: 'assistance',
        loadChildren: () => import('./assistance/assistance.module').then(m => m.MjkAssistanceModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MjkEntityModule {}
